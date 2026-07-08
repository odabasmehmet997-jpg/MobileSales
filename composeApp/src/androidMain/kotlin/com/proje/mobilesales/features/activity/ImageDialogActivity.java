package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.AppCompatImageButton;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.features.model.FicheImageProp;
import java.io.IOException;
import java.io.InputStream;
import kotlin.io.IOStreams;
import org.kxml2.wap.Wbxml;
 
public class ImageDialogActivity extends BaseErpActivity {
    public static final int REQUEST_CAMERA_CODE = 997;
    public static final int REQUEST_SELECT_FILE_CODE = 996;
    private AppCompatImageButton cancelImage;
    private AppCompatImageButton deleteImage;
    private FicheImageProp imageProp;
    private ImageView imageView;
    AlertDialogBuilder mPhotoAlertDialogBuilder;
    private String mUserChoice;
    private AppCompatImageButton saveImage;
    private AppCompatImageButton updateImage;
    private int position = -1;
    final int select = -1;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher = this.registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() {
        public void onActivityResult(final Object obj) {
            lambdanew0((Uri) obj);
        }
    });
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_image_dialog);
        this.setResources();
        this.getIntentExtras();
        if (null != this.imageProp.getImageArray()) {
            this.setImageView();
        } else {
            this.openAddPhotoDialog();
        }
    }

    private void getIntentExtras() {
        imageProp = this.getIntent().getParcelableExtra(IntentExtraName.EXTRAS_IMAGE);
        position = this.getIntent().getIntExtra(IntentExtraName.EXTRAS_POSITON, -1);
    }

    private void setResources() {
        imageView = this.findViewById(R.id.img_photo);
        updateImage = this.findViewById(R.id.updateImage);
        saveImage = this.findViewById(R.id.saveImage);
        cancelImage = this.findViewById(R.id.cancelImage);
        deleteImage = this.findViewById(R.id.deleteImage);
        updateImage.setOnClickListener(this.ClickListener());
        saveImage.setOnClickListener(this.ClickListener());
        cancelImage.setOnClickListener(this.ClickListener());
        deleteImage.setOnClickListener(this.ClickListener());
    } 
    private View.OnClickListener ClickListener() {
        return new View.OnClickListener() {  
            public void onClick(final View view) {
                if (view != updateImage) {
                    if (view != saveImage) {
                        if (view != cancelImage) {
                            if (view == deleteImage) {
                                deleteImage();
                                return;
                            }
                            return;
                        }
                        cancelImage();
                        return;
                    }
                    saveImage();
                    return;
                }
                openAddPhotoDialog();
            }
        };
    }

    public void saveImage() {
        final Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRAS_IMAGE, imageProp);
        intent.putExtra(IntentExtraName.EXTRAS_POSITON, position);
        this.setResult(-1, intent);
        this.finish();
    }

    private void setImageView() {
        try {
            imageView.setImageBitmap(CompressUtil.deCompressBitmap(imageProp.getImageArray()));
        } catch (final IOException e2) {
            Toast.makeText(this, e2.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed() {
        this.setResult(0);
        super.onBackPressed();
    }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (996 == i2) {
            if (-1 == i3) {
                this.onSelectFromGalleryResult(intent);
            }
        } else if (997 == i2 && -1 == i3) {
            this.onCaptureImageResult(intent);
        }
    }  
    public void lambdanew0(final Uri uri) {
        if (null != uri) {
            this.onImagePicked(uri);
        }
    }

    private void onImagePicked(final Uri uri) {
        final FicheImageProp ficheImageProp;
        try {
            final InputStream openInputStream = this.getContentResolver().openInputStream(uri);
            final byte[] readBytes = null != openInputStream ? IOStreams.readBytes(openInputStream) : null;
            if (null == readBytes || null == (ficheImageProp = this.imageProp)) {
                return;
            }
            ficheImageProp.setImageArray(readBytes);
            this.setImageView();
        } catch (final Exception e2) {
            e2.printStackTrace();
        }
    }

    public void openAddPhotoDialog() {
        mPhotoAlertDialogBuilder.setTitle(R.string.str_add_photo).setSingleChoice(R.array.add_photo_select_values, -1, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                lambdaopenAddPhotoDialog1(dialogInterface, i2);
            }
        }).create().show();
    }
    public void lambdaopenAddPhotoDialog1(final DialogInterface dialogInterface, final int i2) {
        mUserChoice = this.getResources().getStringArray(R.array.add_photo_select_values)[i2];
        dialogInterface.dismiss();
        this.startUserChooseIntent();
    }

    public void deleteImage() {
        imageProp.reset();
        this.setImageView();
    }

    public void cancelImage() {
        this.setResult(0);
        this.finish();
    }

    private void startUserChooseIntent() {
        if (mUserChoice.equals(this.getString(R.string.str_choose_from_library))) {
            if (33 <= Build.VERSION.SDK_INT) {
                photoPickerLauncher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
                return;
            } else {
                this.createGalleryIntent();
                return;
            }
        }
        if (mUserChoice.equals(this.getString(R.string.str_take_photo)) && PermissionUtils.checkPermission(this, "android.permission.CAMERA", this.getString(R.string.str_camera_permission_for_take_photo))) {
            this.createCameraIntent();
        }
    }

    private void createCameraIntent() {
        final Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (null != intent.resolveActivity(getPackageManager())) {
            this.startActivityForResult(intent, 997);
        }
    }

    private void createGalleryIntent() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        this.startActivityForResult(Intent.createChooser(intent, "Select File"), 996);
    }

    private void onSelectFromGalleryResult(final Intent intent) {
        if (null != intent) {
            try {
                final Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getApplicationContext().getContentResolver(), intent.getData());
                final int height = bitmap.getHeight();
                final int width = bitmap.getWidth();
                int i2 = Wbxml.OPAQUE;
                int i3 = 260;
                if (height <= width) {
                    i3 = 195;
                    i2 = 260;
                }
                this.setImage(CompressUtil.compressBitmapToJpeg(ThumbnailUtils.extractThumbnail(bitmap, i2, i3, 2)));
            } catch (final IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(final Uri uri) {
        Cursor cursor;
        String str = null;
        try {
            cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]}, null);
        } catch (final Exception e2) {
            e2.printStackTrace();
            cursor = null;
        }
        try {
            final int columnIndex = cursor.getColumnIndex("_data");
            cursor.moveToFirst();
            str = cursor.getString(columnIndex);
            cursor.close();
            return str;
        } catch (final NullPointerException e3) {
            e3.printStackTrace();
            return str;
        }
    }

    private void onCaptureImageResult(final Intent intent) {
        if (null != intent) {
            try {
                this.setImage(CompressUtil.compressBitmapToJpeg((Bitmap) intent.getExtras().get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)));
            } catch (final Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void setImage(final byte[] bArr) {
        if (null != bArr) {
            imageProp.setImageArray(bArr);
            this.setImageView();
        }
    }
}
